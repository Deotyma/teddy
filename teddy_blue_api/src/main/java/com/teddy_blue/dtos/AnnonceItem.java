package com.teddy_blue.dtos;

import java.time.LocalDate;

public interface AnnonceItem {
    
    String getTitle();
    String getTextAnnonce();
    String getPhotoLink();
    String getUserName(); 
    String getSharingMethodName();
    String getCategoryName();
    LocalDate getDateAdded();

}
