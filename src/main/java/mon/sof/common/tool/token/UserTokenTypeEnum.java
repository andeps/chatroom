package mon.sof.common.tool.token;

public enum UserTokenTypeEnum {

    TOKEN("token");

    private String name;

    public String getName() {
        return name;
    }

    UserTokenTypeEnum(String name) {
        this.name = name;
    }

}
