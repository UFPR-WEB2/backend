# backend

<h1>Necessario para Rodar</h1>
<p>Java JDK 17+</p>
<p>Apache Tomcat (Minha versao: 10.1.31)</p>

<h2>Extensoes para Visual Studio Code</h2>
<ul>
    <li>Extension Pack for Java</li>
    <li>Spring Boot Extension Pack</li>
</ul>

<h2>Como funciona criacao do nosso MVC</h2>
<ul>
    <li>Criar model usando o @Entity caso va para o banco de dados!!</li>
    <li>Caso o model tenha o @Entity necessario criar o repository para o model</li>
    <li>Apos isso criar o Controller @RestContoller e realizar injecao de dependencia do model @Autowired</li>
    <li>E o Controller deve seguir o padrao REST, implementar metodos basicos dele</li>
<ul>