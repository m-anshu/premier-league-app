package com.pl.premier_zone.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="player_data") //we do this because the class name i.e Player is not the same as the table name i.e player_data
public class Player {
    @Id //specifies the primary key of the entity
    @Column(name="player_name",unique=true) //Column tells springboot where to find the attribute in the database table , unique=true means we want only unique values as they will form the key
    private String name;

    @Column(name="nation")
    private String nation;

    @Column(name="position")
    private String pos;

    @Column(name="age")
    private Integer age;

    @Column(name="matches_played")
    private Integer mp;

    @Column(name="starts")
    private Integer starts;

    @Column(name="minutes_played")
    private Double min;

    @Column(name="goals")
    private Double gls;

    @Column(name="assists")
    private Double ast;

    @Column(name="penalties_scored")
    private Double pk;

    @Column(name="yellow_cards")
    private Double crdy;

    @Column(name="red_cards")
    private Double crdr;

    @Column(name="expected_goals")
    private Double xg;

    @Column(name="expected_assists")
    private Double xag;

    @Column(name="team_name")
    private String team;

    public Player() {
    }

    public Player(String name, String team, Double xag, Double xg, Double crdr, Double crdy, Double pk, Double ast, Double gls, Double min, Integer starts, Integer mp, Integer age, String pos, String nation) {
        this.name = name;
        this.team = team;
        this.xag = xag;
        this.xg = xg;
        this.crdr = crdr;
        this.crdy = crdy;
        this.pk = pk;
        this.ast = ast;
        this.gls = gls;
        this.min = min;
        this.starts = starts;
        this.mp = mp;
        this.age = age;
        this.pos = pos;
        this.nation = nation;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public String getPos() {
        return pos;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getMp() {
        return mp;
    }

    public Integer getStarts() {
        return starts;
    }

    public Double getMin() {
        return min;
    }

    public Double getGls() {
        return gls;
    }

    public Double getAst() {
        return ast;
    }

    public Double getPk() {
        return pk;
    }

    public Double getCrdy() {
        return crdy;
    }

    public Double getCrdr() {
        return crdr;
    }

    public Double getXg() {
        return xg;
    }

    public Double getXag() {
        return xag;
    }

    public String getTeam() {
        return team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public void setGls(Double gls) {
        this.gls = gls;
    }

    public void setAst(Double ast) {
        this.ast = ast;
    }

    public void setPk(Double pk) {
        this.pk = pk;
    }

    public void setCrdy(Double crdy) {
        this.crdy = crdy;
    }

    public void setCrdr(Double crdr) {
        this.crdr = crdr;
    }

    public void setXg(Double xg) {
        this.xg = xg;
    }

    public void setXag(Double xag) {
        this.xag = xag;
    }

    public void setTeam(String team) {
        this.team = team;
    }

}
