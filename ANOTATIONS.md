


Fluxo da requisição: ao clicar em Send no Postman com o POST -> o Spring recebeu o JSON -> o @RequestBody traduziu para Java -> o SubjectController acionou o SubjectRepository -> que por sua vez usou o .save() para injetar a "Programação" no banco de dados