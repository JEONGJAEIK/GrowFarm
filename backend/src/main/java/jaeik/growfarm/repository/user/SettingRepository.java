package jaeik.growfarm.repository.user;

import jaeik.growfarm.entity.user.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * 설정 Repository
 * 설정 관련 데이터베이스 작업을 수행하는 Repository
 * 수정일 : 2025-05-03
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

}
