<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form class="input" method="POST" action="./FeedController">
            <input name="add" placeholder="https://www.diepresse.com/rss/Politik" value="" />
            <button type="submit">Feed hinzufügen</button>
        </form>
        <div class="feeds">
            <c:forEach items="${applicationScope.channels}" var="channel">
                <div>
                    <form class="title" method="POST" action="./FeedController">
                        <h1>
                            ${channel.title}
                            <a href="${channel.link}">${channel.link}</a>
                        </h1>
                        <input name="remove" type="text" value="${channel.id}" hidden>
                        <button type="submit">Feed entfernen</button>
                        <button type="button" onclick="onToggleCollapse(event)">Minimieren</button>
                    </form>
                    <c:forEach items="${channel.items}" var="feed">
                        <section class="feed ${feed.hidden ? 'hidden' : ''} ${feed.read ? 'read' : ''}">
                            <img src="${feed.enclosure.url != null ? feed.enclosure.url : 'https://miro.medium.com/max/1400/1*KOAfAOQ9FwAp9i2muTkGWw.png'}">
                            <div>
                                <h2>${feed.title}</h2>
                                <p>${feed.description}</p>
                                <span>${feed.formattedDate}</span>
                            </div>
                            <section class="options">
                                <c:if test="${!feed.read}">
                                    <form method="POST" action="./FeedController">
                                        <input type="text" name="read" value="${feed.id}" hidden>
                                        <input type="text" name="channel" value="${channel.id}" hidden>
                                        <button type="submit">Gelesen</button>
                                    </form>
                                </c:if>
                                <form method="POST" action="./FeedController">
                                    <input type="text" name="hide" value="${feed.id}" hidden>
                                    <input type="text" name="channel" value="${channel.id}" hidden>
                                    <button type="submit">Verstecken</button>
                                </form>
                                <button type="button" onclick="onView('${feed.link}')">Anzeigen</button>
                            </section>
                            <form method="POST" action="./FeedController" class="unhide-container">
                                <input type="text" name="show" value="${feed.id}" hidden>
                                <input type="text" name="channel" value="${channel.id}" hidden>
                                <button class="unhide" type="submit">
                                    Anzeigen
                                </button>
                            </form>
                        </section>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>

        <script>

            const onView = (link) => {
                window.open(link);
            };

            const onToggleCollapse = (event) => {
                const el = event.target;
                const itemContaienr = Array.from(el.parentNode.parentNode.querySelectorAll('.feed'));
                itemContaienr.forEach(item => {
                    const curr = item.style.display;

                    if (curr === 'flex' || !curr) {
                        item.style.display = 'none';
                        el.innerHTML = 'Maximieren';
                    } else {
                        item.style.display = 'flex';
                        el.innerHTML = 'Minimieren';
                    }
                })
            };

        </script>
    </body>
</html>
