package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// repository는 spring data jpa가 제공하는 인터페이스 
// 엔티티 db의 테이블과 구조를 생성하는데 사용했다면 리포지토리는 엔티티가 생성한 db에 접근하는데 사용 
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
