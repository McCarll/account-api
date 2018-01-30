package ru.mccarl.account.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by vrudometkin on 27/01/2018.
 */
@Data
@EqualsAndHashCode
public class Account {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String count;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currency;

}
