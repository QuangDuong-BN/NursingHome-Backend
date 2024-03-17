package com.example.nursinghome.projectioninterface;

import com.example.nursinghome.enumcustom.GenderUser;

public interface UserProjection {
    Long getId();
    String getName();
    String getAddress();
    GenderUser getGender();
    String getImageUrl();
}