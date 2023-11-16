package com.fledson.dslist.projections;

/* projeções são interfaces e precisam ter o método get dos campos projetados na consulta sql manual*/
public interface GameMinProjection {
    Long getId();
    String getTitle();
    Integer getGameYear();
    String getImgUrl();
    String getShortDescription();
    Integer getPosition();
}
