package com.mandisoft.services.member.utility;

import org.springframework.stereotype.Component;

@Component
public class GridUtil {
	
	 /**
	 * @param currentPage
	 * @param totalRowsToFetch
	 * @return Integer startIndex
	 */
	public Integer calculateStartIndexForList(final Integer currentPage, final Integer totalRowsToFetch) {
	  Integer startIndex = (currentPage * totalRowsToFetch) - totalRowsToFetch;
	  return startIndex;
	 }
}
