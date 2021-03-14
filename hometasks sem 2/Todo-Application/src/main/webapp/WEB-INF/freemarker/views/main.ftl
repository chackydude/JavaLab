<#import "spring.ftl" as spring/>
<#import "tags/header.ftl" as header/>
<#import "tags/group.ftl" as userGroup/>
<#import "tags/todo.ftl" as userTodo/>
<#import "tags/pieChart.ftl" as pieChart/>
<#import "tags/headerImports.ftl" as imports/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main</title>
    <link rel="stylesheet" href='<@spring.url "/styles/main.css"/>' type="text/css">
    <link rel="stylesheet" href="<@spring.url '/styles/header.css' />" type="text/css">
    <link rel="icon" href='<@spring.url "/assets/favicon.ico"/>' type="image/x-icon" />
    <@imports.imports />
</head>
<body>
    <script src="<@spring.url "/scripts/todo.js"/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
    <script src="https://yastatic.net/jquery/3.3.1/jquery.min.js"></script>

    <@header.header />
    <div class="main-content">
        <div class="user">
            <div class="user__name">
                <p><@spring.message "main_page.user"/>:</p>
                <p>${currentUser.getName()}</p>
                <div class="button-container">
                    <form action="${ springMacroRequestContext.contextPath }/sign-out" method="get"><input type="submit" class="sign-out-button" value="<@spring.message "main_page.sign_out"/>"></form>
                    <button class="sign-out-button filter-button" id="filter-button"><@spring.message "main_page.filter"/></button>
                </div>
            </div>
            <div class="user__groups">

                <a href="<@spring.url "/main"/>"><@userGroup.group title="all todos" color="#f2eab2"/></a>


                <#list groups as group>
                    <form action="filter-todos" method="get">
                        <button type="submit" value="${group.getId()}" name="group">
                            <@userGroup.group  title="${group.getName()}" color=""/>
                        </button>
                    </form>
                </#list>
            </div>
        </div>
        <div class="todo-list">
            <#if todos?has_content>
                <#list todos as todo>
                    <@userTodo.todo text="${todo.getText()}" id="${todo.getId()}" todoGroup="${todo.getGroup().getId()}"/>
                </#list>
                <form class="todo-form" action="add-todo" method="post">
                    <input type="text" name="todoText" placeholder="<@spring.message "main_page.task.placeholder"/>">
                    <button type="submit"><@spring.message "main_page.task.add"/></button>
                </form>

                <#if pageAmount?? && size??>
                    <div class="pagination">
                        <#list 1..pageAmount as index>
                            <a href="<@spring.url "/main/?page=${index - 1}&size=${size}"/>">${index}</a>
                        </#list>
                    </div>
                </#if>

            <#else>
                <form class="todo-form" action="add-todo" method="post">
                    <input type="text" name="todoText" placeholder="<@spring.message "main_page.task.placeholder"/>">
                    <button type="submit"><@spring.message "main_page.task.add"/></button>
                </form>
                <div class="empty-todos">
                    <div>
                        <img src="${ springMacroRequestContext.contextPath }/assets/empty-todos-image.png"  alt="emty-todos" class="empty-todos-image">
                        <p><@spring.message "main_page.empty_text"/></p>
                    </div>
                </div>
            </#if>
        </div>
        <div class="statistics">
            <p><@spring.message "main_page.activity"/>:</p>
            <@pieChart.pieChart userId="${ currentUser.getId() }"/>
        </div>
    </div>
    <script src="<@spring.url "/scripts/viewFilter.js"/>"></script>
</body>
</html>
