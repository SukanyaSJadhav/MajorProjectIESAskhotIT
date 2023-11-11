package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.UserInfoEntity;

public interface UserInfoRepository extends JpaRepository <UserInfoEntity, Integer> {

}
