package io.tortoise.controller.request.organization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryOrgMemberRequest {
    private String name;
    private String organizationId;
}
