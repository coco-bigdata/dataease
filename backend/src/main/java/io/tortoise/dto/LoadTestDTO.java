package io.tortoise.dto;

import io.tortoise.base.domain.LoadTest;
import io.tortoise.base.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadTestDTO extends LoadTest {
    private String projectName;
    private String userName;
    private Schedule schedule;
}
