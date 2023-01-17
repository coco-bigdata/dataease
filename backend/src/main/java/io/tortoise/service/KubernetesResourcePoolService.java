package io.tortoise.service;

import io.tortoise.dto.TestResourcePoolDTO;

public interface KubernetesResourcePoolService {
    boolean validate(TestResourcePoolDTO testResourcePool);
}
