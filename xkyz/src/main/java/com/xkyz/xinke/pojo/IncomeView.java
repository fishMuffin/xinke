package com.xkyz.xinke.pojo;

import com.xkyz.xinke.model.StorePoints;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeView {
    public IncomeView(Double count, Double incomeOfToday, Double incomeOfAll) {
        this.count = count;
        this.incomeOfToday = incomeOfToday;
        this.incomeOfAll = incomeOfAll;
    }

    public IncomeView() {
    }

    private Double count;
    private Double incomeOfToday;
    private Double incomeOfAll;
}
