package io.github.xiaoyureed.shopeesearch.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : xiaoyureed
 * 2020/10/20
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        /*
        >>> serialization config
         */
        // ignore null field in java bean
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        /*
        >>> deserialization config
         */
        // allow json string contains unknown fields that doesn't exit in java bean
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // config filter
//        new SimpleBeanPropertyFilter.FilterExceptFilter()
//        objectMapper.setFilterProvider()
        // or @JsonIgnore on field
        // or @JsonIgnoreProperties(value = { "intValue" }) on type
        //or ignore all field by @JsonIgnoreType on type

        //or @JsonIgnoreType on mixin type(empty type) to ignore fields of the java bean which we have no access to
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.addMixIn(String[].class, MyMixInForIgnoreType.class);
//Note: Since version 2.5 – it seems that we can not use this method to ignore primitive data types, but we can use it for custom data types and arrays.

        // @JsonRawValue on json string field to serialize raw json string

        //@JsonValue on method to customized serialization

        //@JsonRootName(value = "user") to wrapper a root property for output json

        //@JsonSerialize(using = CustomDateSerializer.class) on field to specify a customized serializer
        // ex: class CustomDateSerializer extends StdSerializer<Date>

        return objectMapper;
    }
}
