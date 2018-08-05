package com.gh.base;

import com.gh.utils.Condition;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class BaesColntroller {

    private static final Logger logger = LoggerFactory.getLogger(BaesColntroller.class);
    @Getter
    private Condition condition;
    public boolean bindingResult(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            condition = new Condition();
            List<ObjectError> objectErrors = bindingResult.getAllErrors();

            objectErrors.forEach(objectError -> {
                condition.setName("errer");
                condition.setValue(objectError.getDefaultMessage());
                logger.info("有错误" + objectError.getDefaultMessage());
            });
            return false;
        }
        return true;
    }
}
