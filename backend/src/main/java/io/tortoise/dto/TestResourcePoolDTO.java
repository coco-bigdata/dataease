package io.tortoise.dto;

import io.tortoise.base.domain.TestResource;
import io.tortoise.base.domain.TestResourcePool;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestResourcePoolDTO extends TestResourcePool {

    private List<TestResource> resources;

}
