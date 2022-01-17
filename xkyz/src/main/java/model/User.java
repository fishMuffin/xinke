package model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;

    public Integer getId() {
        return id;
    }
   //熟悉流程test
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
