package jaeik.growfarm.entity.crop;

import jaeik.growfarm.entity.user.Users;
import jaeik.growfarm.global.security.MessageEncryptConverter;
import jaeik.growfarm.repository.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// 농작물 엔티티
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "crop", uniqueConstraints =
        {@UniqueConstraint(name = "unique_user_x_y", columnNames = {"user_id", "width", "height"})})
public class Crop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "crop_type", nullable = false)
    private CropType cropType;

    @NotNull
    @Column(nullable = false, length = 8) // 익명 닉네임 8자 까지 허용
    private String nickname;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    @Convert(converter = MessageEncryptConverter.class)
    private String message; // 익명 메시지 255자 까지 지만 암호화 때문에 TEXT로 설정

    @NotNull
    @Column(nullable = false)
    private int width;

    @NotNull
    @Column(nullable = false)
    private int height;
}