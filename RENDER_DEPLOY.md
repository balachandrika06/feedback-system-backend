# Deploying to Render

This service is ready to deploy on Render as a Docker-based web service.

## Recommended setup

1. Push this repository to GitHub.
2. In Render, create a new Web Service from the repo.
3. Render should detect `render.yaml` automatically.
4. Set these environment variables in Render before the first successful boot:
   - `CORS_ALLOWED_ORIGINS`
   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`

## Required environment values

Example MySQL connection string:

```text
jdbc:mysql://host:3306/database?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
```

Example CORS value:

```text
https://your-frontend.onrender.com,https://your-frontend.vercel.app
```

## Notes

- Render provides the `PORT` environment variable automatically, and the app now reads it.
- `JWT_SECRET` is generated automatically by Render from the blueprint.
- If you use Render PostgreSQL, this app will need code changes because it currently uses MySQL.
