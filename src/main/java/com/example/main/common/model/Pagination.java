package com.example.main.common.model;

import lombok.Getter;

@Getter
public class Pagination {
	
	private int totalRecordCount;		// 전체 데이터 수
	private int totalPageCount;			// 전체 페이지 수
	private int startPage;				// 첫 페이지 번호
	private int endPage;				// 끝 페이지 번호
	private int limitStart;				// LIMIT 시작 위치
	private boolean existPrevPage;		// 이전 페이지 존재 여부
	private boolean existNextPage;		// 다음 페이지 존재 여부
	
	public Pagination(int totalRecordCount, Integer currentPage, Integer pageUnit) {
		if(totalRecordCount > 0) {
			this.totalPageCount = totalRecordCount;
			calculation(currentPage, pageUnit);
		}
	}
	
	private void calculation(Integer currentPage, Integer pageUnit) {
		
		final int pageSize = 10;
		
		// 전체 페이지 수 계산
		totalPageCount = ((totalRecordCount - 1) / pageUnit) + 1;
		
		// 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
		if(currentPage > totalPageCount) {
			currentPage = totalPageCount;
		}
		
		// 첫 페이지 번호 계산
		startPage = ((currentPage - 1) / pageSize) * pageSize + 1;
		
		// 끝 페이지 번호 계산
		endPage = startPage + pageSize -1;
		
		// 끝 페이지가 전체 페이지 수보다 큰 경우, 큰 페이지 전체 페이지 수 저장
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		// LIMIT 시작 위치 계산
		limitStart = (currentPage - 1) * pageUnit;
		
		// 이전 페이지 존재 여부 확인
		existPrevPage = currentPage != 1;
		
		// 다음 페이지 존재 여부 확인
		existNextPage = (currentPage * pageUnit) < totalRecordCount;
	}

}
