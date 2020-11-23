package com.sst.projectService.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.ConstructorArgs;

/**
 * <p>
 * 
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Sharedlibrary对象", description="")
public class Sharedlibrary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公共库id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "公共库名")
    private String name;


    public Sharedlibrary(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
