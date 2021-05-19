package org.youyuan.vhr.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.youyuan.vhr.bean.Hr;

public class HrUtils {
    public static Hr getHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
