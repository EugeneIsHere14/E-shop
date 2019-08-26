<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${pageQuantity lt 5}">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="products?${addressBar}&currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${pageQuantity}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="products?${addressBar}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt pageQuantity}">
                <li class="page-item"><a class="page-link"
                                         href="products?${addressBar}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </c:when>

    <c:otherwise>
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="products?${addressBar}&currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:choose>
                <c:when test="${currentPage eq 1}">
                    <li class="page-item active"><a class="page-link">
                        1 <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="products?${addressBar}&currentPage=1">1</a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:if test="${currentPage < 4}">
                <c:forEach begin="2" end="${i + 4}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="products?${addressBar}&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>

            <c:if test="${currentPage gt 3 && pageQuantity - 1 gt 4}">
                <li class="page-item" id="first-dots"><span class="page-link"><b>. . .</b></span>
                </li>
            </c:if>

            <c:if test="${currentPage gt 3 && currentPage lt pageQuantity - 1}">
                <c:forEach begin="${currentPage - 1}" end="${currentPage + 1}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="products?${addressBar}&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>

            <c:if test="${currentPage >= pageQuantity - 1}">
                <c:forEach begin="${pageQuantity - 3}" end="${pageQuantity - 1}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="products?${addressBar}&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>

            <c:if test="${currentPage lt pageQuantity - 2 && pageQuantity - 1 gt 4}">
                <li class="page-item" id="second-dots"><span class="page-link"><b>. . .</b></span>
                </li>
            </c:if>

            <c:choose>
                <c:when test="${currentPage eq pageQuantity}">
                    <li class="page-item active"><a class="page-link">
                            ${pageQuantity} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="products?${addressBar}&currentPage=${pageQuantity}">${pageQuantity}</a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:if test="${currentPage lt pageQuantity}">
                <li class="page-item"><a class="page-link"
                                         href="products?${addressBar}&currentPage=${currentPage+1}">Next</a>
                </li>

            </c:if>
        </ul>
    </c:otherwise>
</c:choose>