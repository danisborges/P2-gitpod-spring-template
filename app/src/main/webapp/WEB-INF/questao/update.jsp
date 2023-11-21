<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Editar Questao</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <h1>Editar Questao</h1>
        <form action="/questao/update" method="post">
            <input type="hidden" name="id" value="${questao.id}">
            <div class="form-group">
                <label for="titulo">Titulo</label>
                <input type="text" class="form-control" id="titulo" name="titulo" value="${questao.titulo}">
            </div>
            
            <div class="form-group">
                <label for="alternativa">Categoria</label>
                <select name="alternativa" id="" class="form-select">
                    <c:forEach var="c" items="${alternativas}">
                        <option value="${c.id}" ${questao.alternativa.id==c.id ? 'selected' : '' }>${c.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <a href="/questao/list" class="btn btn-primary">Voltar</a>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</body>
</html>