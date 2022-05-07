package practice.datastruc.obj;

/**
 * @author shiLong
 * @version 1.0
 * @desc
 * @date 2022/2/17
 */
public class Constant {
    public enum PlanStatusEnum {

        READY_TO_PUBLISH(0, "待发布"),
        PUBLISHING(1, "发布中"),
        PUBLISH_SUCCEED(2, "发布成功"),
        PART_SUCCEED(3, "部分成功"),
        PUBLISH_FAILED(4, "发布失败"),
        ALREADY_FINISHED(5, "已结束"),
        ;
        /**
         * 计划状态
         */
        private Integer planStatus;

        /**
         * 计划状态字符串值
         */
        private String planStatusString;

        PlanStatusEnum(Integer planStatus, String planStatusString) {
            this.planStatus = planStatus;
            this.planStatusString = planStatusString;
        }

        public Integer getPlanStatus() {
            return planStatus;
        }

        public String getPlanStatusString() {
            return planStatusString;
        }
    }
}
