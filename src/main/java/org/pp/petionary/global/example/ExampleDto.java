package org.pp.petionary.global.example;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExampleDto {

    private String example;

    public ExampleDto(String example){
        this.example = example;
    }
}