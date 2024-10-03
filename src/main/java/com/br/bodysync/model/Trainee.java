package com.br.bodysync.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("2")
public class Trainee extends User {

    @Column
    private boolean hasAlreadyTrained;

    @Column
    private Double time;

    @ManyToOne
    @JoinColumn(name = "objective_id")
    private Objective objective;

    @ManyToOne
    @JoinColumn(name = "personal_trainer_id")
    private PersonalTrainer personalTrainer;

    public Objective getObjective() {
        return objective;
    }

    public Trainee() {
        super();
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public boolean isHasAlreadyTrained() {
        return hasAlreadyTrained;
    }

    public void setHasAlreadyTrained(boolean hasAlreadyTrained) {
        this.hasAlreadyTrained = hasAlreadyTrained;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public PersonalTrainer getPersonalTrainer() {
        return personalTrainer;
    }

    public void setPersonalTrainer(PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;
    }

}
