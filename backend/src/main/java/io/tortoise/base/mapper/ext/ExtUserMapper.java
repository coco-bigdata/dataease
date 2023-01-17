package io.tortoise.base.mapper.ext;

import io.tortoise.base.domain.User;
import io.tortoise.controller.request.UserRequest;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExtUserMapper {

    List<User> getUserList(@Param("userRequest") UserRequest request);

    int updatePassword(User record);

    String getDefaultLanguage(String paramKey);

    List<User> searchUser(String condition);

    @MapKey("id")
    Map<String, User> queryNameByIds(List<String> userIds);
}
