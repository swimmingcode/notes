package org.youyuan.vhr.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    private long total;
    private List<?> data = new ArrayList<>();
}
