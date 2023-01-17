package io.tortoise.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String name;
    private String type;
}
