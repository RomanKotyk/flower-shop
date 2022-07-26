<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.07.2022
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jstl:choose>
    <jstl:when test="${not empty sessionScope.user}">
        <p>${sessionScope.user.name}</p>
        <p><a href="controller?action=bunches">My bunch</a></p>
        <p><a href="controller?action=logout">Logout</a></p>
    </jstl:when>
    <jstl:otherwise>
        <p><a href="register.jsp">Register</a></p>
        <p><a href="login.jsp">Login</a></p>
    </jstl:otherwise>
</jstl:choose>

<jstl:choose>
    <jstl:when test="${empty sessionScope.user}">
        <jstl:forEach items="${requestScope.flowers}" var="flower">
            <br/>
            <p><img src="${flower.imagePath}" alt="flower" /></p>
            <p>Name: ${flower.name}</p>
            <p>Price: ${flower.price}</p>
            <p>Length Steak: ${flower.lengthSteack}</p>
            <p>Ice level: ${flower.iceLevel}</p>

            <form method="post" action="controller?action=register">
                <input type="submit" value="Register for add this flower to the bunch" />
            </form>
        </jstl:forEach>
    </jstl:when>
    <jstl:otherwise>

        <jstl:choose>
            <jstl:when test="${sessionScope.user.admin == false}">

                <form method="post" action="controller?action=create_new_bunch">
                    <input type="submit" value="Create new bunch" />
                </form>


                <jstl:forEach items="${requestScope.bunches}" var="bunch">

                    <p>Bunch id: ${bunch.id}</p>

                    <form method="post" action="controller?action=add_to_bunch">

                        <input type="hidden" name="id" value="${bunch.id}" />

                        <input type="submit" value="Add to bunch" />

                    </form>

                    <form method="post" action="controller?action=bunch_flowers">

                        <input type="hidden" name="id" value="${bunch.id}" />

                        <input type="submit" value="Flowers of this bunch" />

                    </form>


                </jstl:forEach>

            </jstl:when>
            <jstl:otherwise>
                <a href="controller?action=add_new_flower">Add new flower</a>
                <form method="post" action="controller?action=create_new_bunch">
                    <input type="submit" value="Create ready bunch" />
                </form>
            </jstl:otherwise>
        </jstl:choose>

    </jstl:otherwise>
</jstl:choose>





</body>
</html>

