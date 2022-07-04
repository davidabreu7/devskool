package com.devsuperior.dslearn.entities;

import com.devsuperior.dslearn.entities.enums.DeliverStatus;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity(name = "tb_deliver")
public class Deliver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uri;
    private Instant moment;
    private DeliverStatus status;
    private String feedback;
    private Integer correctCount;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumns({
                    @JoinColumn(name = "user_id"),
                    @JoinColumn(name = "offer_id")
                })
    private Enrollment enrollment;

    public Deliver() {
    }

    public Deliver(String uri, Instant moment, DeliverStatus status, String feedback, Integer correctCount, Lesson lesson, Enrollment enrollment) {
        this.uri = uri;
        this.moment = moment;
        this.status = status;
        this.feedback = feedback;
        this.correctCount = correctCount;
        this.lesson = lesson;
        this.enrollment = enrollment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public DeliverStatus getStatus() {
        return status;
    }

    public void setStatus(DeliverStatus status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrrollment) {
        this.enrollment = enrrollment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliver deliver = (Deliver) o;
        return Objects.equals(id, deliver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Deliver{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", moment=" + moment +
                ", status=" + status +
                ", feedback='" + feedback + '\'' +
                ", correctCount=" + correctCount +
                ", lesson=" + lesson +
                ", enrrollment=" + enrollment +
                '}';
    }
}


