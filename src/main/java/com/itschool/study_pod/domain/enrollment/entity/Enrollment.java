package com.itschool.study_pod.domain.enrollment.entity;

import com.itschool.study_pod.domain.studygroup.dto.response.StudyGroupResponse;
import com.itschool.study_pod.domain.user.dto.response.UserResponse;
import com.itschool.study_pod.domain.enrollment.dto.request.EnrollmentRequest;
import com.itschool.study_pod.domain.enrollment.dto.response.EnrollmentResponse;
import com.itschool.study_pod.domain.studygroup.entity.StudyGroup;
import com.itschool.study_pod.domain.user.entity.User;
import com.itschool.study_pod.global.base.BaseEntity;
import com.itschool.study_pod.global.enumclass.EnrollmentStatus;
import com.itschool.study_pod.global.base.crud.Convertible;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "enrollments")
@SQLDelete(sql = "UPDATE enrollments SET is_deleted = true WHERE enrollment_id = ?")
@Where(clause = "is_deleted = false")
public class Enrollment extends BaseEntity implements Convertible<EnrollmentRequest, EnrollmentResponse> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long id;

    /*@Column(nullable = false)
    private LocalDateTime appliedAt;*/

    @Column(nullable = false)
    private String introduce;

    /*private LocalDateTime joinedAt;*/

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_group_id", nullable = false)
    private StudyGroup studyGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private User user;

    public static Enrollment of(EnrollmentRequest request) { // create용
        return Enrollment.builder()
                // .appliedAt(LocalDateTime.now())
                .introduce(request.getIntroduce())
                .status(EnrollmentStatus.PENDING)
                .user(User.withId(request.getUser().getId()))
                .studyGroup(StudyGroup.withId(request.getStudyGroup().getId()))
                .build();
    }

    @Override
    public void update(EnrollmentRequest request) {
        this.introduce = request.getIntroduce();
        this.status = request.getStatus();
        
        // 아래의 소속 및 유저가 변경되는 안됨 (fk)
        /*this.studyGroup = request.getStudyGroup() != null?
                StudyGroup.withId(request.getStudyGroup().getId()) : this.studyGroup;
        this.user = request.getUser() != null?
                User.withId(request.getUser().getId()) : this.user;*/
    }

    public void kickMember() {
        this.status = EnrollmentStatus.BANNED;
    }

    public void approveMember() {
        this.status = EnrollmentStatus.APPROVED;
    }

    @Override
    public EnrollmentResponse response() {
        return EnrollmentResponse.builder()
                .id(this.id)
                .introduce(this.introduce)
                .status(this.status)
                .user(UserResponse.withId(this.user.getId()))
                .studyGroup(StudyGroupResponse.withId(this.studyGroup.getId()))
                .createdAt(this.createdAt)
                .createdBy(this.createdBy)
                .updatedAt(this.updatedAt)
                .updatedBy(this.updatedBy)
                // .appliedAt(this.appliedAt)
                // .joinedAt(this.joinedAt)
                .build();
    }

    public static Enrollment withId(Long id) {
        return Enrollment.builder()
                .id(id)
                .build();
    }
}
