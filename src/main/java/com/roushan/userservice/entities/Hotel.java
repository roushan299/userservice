package com.roushan.userservice.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    private String hotelId;
    private String name;
    private String location;
    private String about;
}
