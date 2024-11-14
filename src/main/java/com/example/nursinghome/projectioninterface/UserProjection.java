package com.example.nursinghome.projectioninterface;

import com.example.nursinghome.constants.enums.GenderUser;

public interface UserProjection {
    Long getId();
    String getName();
    String getAddress();
    GenderUser getGender();
    String getImageUrl();
}