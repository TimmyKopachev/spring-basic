package org.dzmitry.kapachou.core.conditional;

import lombok.AllArgsConstructor;

import javax.annotation.PostConstruct;

@AllArgsConstructor
public class CacheManager {

    private final String name;

    @PostConstruct
    public void setup() {
        System.out.println("CacheManager name is " + name);
    }


}
