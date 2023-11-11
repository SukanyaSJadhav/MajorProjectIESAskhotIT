package in.ashokit.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardReponse {

    private Long plansCount;

    private Long citizenApCnt;

    private Long citizenDnCnt;

    private Double benefitAmtTotal;
}
