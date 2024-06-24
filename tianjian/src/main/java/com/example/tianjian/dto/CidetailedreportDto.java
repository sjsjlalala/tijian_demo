package com.example.tianjian.dto;

import com.example.tianjian.entity.Cidetailedreport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CidetailedreportDto {
    private String ciName;
    private List<Cidetailedreport> cidetailedreports;
}
