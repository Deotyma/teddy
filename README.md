# 🧸 Teddy Blue

> Plateforme d'échange de jouets et matériel éducatif pour enfants en situation de handicap  
> *Marketplace for educational toys and materials for children with disabilities*

---

## 🇫🇷 Français

### Le problème

Les jouets et matériels éducatifs homologués pour enfants en situation de handicap sont souvent très coûteux. Pourtant, leur durée d'utilité pédagogique est courte : un enfant acquiert rapidement une compétence, doit passer à un niveau supérieur, et le jouet n'a parfois servi que deux ou trois fois. Les familles se retrouvent avec du matériel onéreux inutilisé, sans moyen simple de l'échanger.

**Teddy Blue** est une plateforme communautaire qui permet aux parents d'enfants handicapés de vendre, acheter, donner ou échanger jouets, livres et matériel éducatif adaptés.

### Fonctionnalités

- 🔐 Authentification sécurisée avec gestion des données personnelles
- 📦 Publication d'annonces : vente, achat, don, échange
- 🔍 Recherche et filtrage du matériel disponible
- 👤 Espace personnel pour gérer ses annonces
- 🗺️ Carte interactive (Leaflet.js) pour trouver du matériel à proximité de chez soi
- 🧩 Catégories : jouets éducatifs, livres adaptés, matériel pédagogique

### Technologies

| Couche | Technologie |
|---|---|
| Backend | Java — Spring Boot |
| Frontend | Vue.js |
| Base de données | PostgreSQL |
| Carte | Leaflet.js + OpenStreetMap |
| Sécurité | Authentification avec protection des données |
| Conception | Mockups UX/UI inclus dans le repo |

### Architecture

```
teddy/
├── teddy_blue_api/     → Backend Spring Boot (API REST)
├── teddy_front/        → Frontend Vue.js
├── teddyDB/            → Scripts PostgreSQL
└── mockup/             → Maquettes UX/UI
```

### Contexte

Projet réalisé dans le cadre du titre RNCP **Concepteur Développeur d'Applications (CDA)**. Né d'une expérience professionnelle en éducation spécialisée et d'une connaissance directe des besoins des familles concernées.

---

## 🇬🇧 English

### The Problem

Approved educational toys and materials for children with disabilities are often very expensive — yet their pedagogical lifespan is short. A child quickly masters a skill, needs to move on to a harder challenge, and the toy may have been used only two or three times. Families are left with costly, unused equipment and no easy way to exchange it.

**Teddy Blue** is a community platform that allows parents of children with disabilities to sell, buy, donate, or exchange adapted toys, books, and educational materials.

### Features

- 🔐 Secure authentication with personal data protection
- 📦 Post listings: sell, buy, donate, or swap
- 🔍 Search and filter available items
- 👤 Personal dashboard to manage listings
- 🗺️ Interactive map (Leaflet.js) to find materials near your location
- 🧩 Categories: educational toys, adapted books, pedagogical tools

### Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java — Spring Boot |
| Frontend | Vue.js |
| Database | PostgreSQL |
| Map | Leaflet.js + OpenStreetMap |
| Security | Authentication & data protection |
| Design | UX/UI mockups included in repo |

### Architecture

```
teddy/
├── teddy_blue_api/     → Spring Boot backend (REST API)
├── teddy_front/        → Vue.js frontend
├── teddyDB/            → PostgreSQL scripts
└── mockup/             → UX/UI wireframes
```

### Context

Built as part of the French **Concepteur Développeur d'Applications (CDA)** certification. Inspired by direct professional experience in special education and first-hand knowledge of the needs of families affected by childhood disability.

---

*Parce que chaque enfant mérite les bons outils pour apprendre, quelle que soit sa situation.*  
*Because every child deserves the right tools to learn, whatever their situation.*
