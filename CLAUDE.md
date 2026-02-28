# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Language

Always respond in Korean (한국어로 답변할 것).

## Build & Run

**Build (WAR):**
```bash
mvn package
```

**Compile only:**
```bash
mvn compile
```

**Run:** Deployed via IntelliJ IDEA's Tomcat 9 run configuration. The app runs at `http://localhost:8080` with context path `/` using an exploded WAR artifact.

There are no test dependencies configured in this project.

## Stack

- Java 8, Spring WebMVC 5.3.39, Servlet API 4.0.1
- Packaging: WAR deployed to Tomcat 9
- Views: JSP under `/WEB-INF/views/`

## Architecture

**Single DispatcherServlet** mapped to `/`, configured in `web.xml`. No separate root application context — only the servlet context defined in `src/main/webapp/WEB-INF/spring/servlet-context.xml`.

**Spring configuration** (`servlet-context.xml`):
- `<mvc:annotation-driven/>` enables annotation-based MVC
- Component scan covers all of `org.example`
- `InternalResourceViewResolver` resolves view names to `/WEB-INF/views/<name>.jsp`

**Encoding:** `CharacterEncodingFilter` (UTF-8, forceEncoding=true) applied to `/*` in `web.xml`.

**Package convention:** Feature controllers go under `org.example.<feature>.controller`. Example: `org.example.blog.controller.BlogController`. JSP views mirror this under `/WEB-INF/views/<feature>/`.

## Routes

| URL | Method | Controller | View |
|-----|--------|------------|------|
| `/`, `/hello` | GET | `HelloController` | `hello.jsp` |
| `/create` | GET | `BlogController` | `blog/create.jsp` |
