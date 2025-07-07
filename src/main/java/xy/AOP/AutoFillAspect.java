package xy.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import xy.constant.AutoFillConstant;
import xy.context.BaseContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    //切入点
    //这个意思是,拦截所有这个注视下的方法
    @Pointcut("@annotation(xy.AOP.AutoFill)")
    public void autoFillPointCut(){}

    //通知
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段自动填充");


        //获取到当前拦截到的方法对数据库的操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);

        OperationType operationType = autoFill.value();

        //获取到当前被拦截方法的参数
        Object[] args = joinPoint.getArgs();//约定第一个参数为对象参数

        if (args.length == 0) {
            return;
        }
        Object arg = args[0];

        //准备要赋值的数据
        LocalDateTime now = LocalDateTime.now();

        Long id = BaseContext.getCurrentId();

        //根据当前不同的操作类型,为对应的属性通过反射来赋值

        if (operationType == OperationType.INSERT) {
            try {
                Method setCreateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setCreateTime.invoke(arg, now);
                setUpdateTime.invoke(arg, now);
                setCreateUser.invoke(arg, id);
                setUpdateUser.invoke(arg, id);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else if (operationType == OperationType.UPDATE) {
            try {
                Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(arg, now);
                setUpdateUser.invoke(arg, id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
