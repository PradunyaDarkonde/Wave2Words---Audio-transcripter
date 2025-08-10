# Wave2Words

Wave2Words is a full-stack application that transcribes audio files to text using a React frontend (Vite), a Spring Boot backend, and integrates with the Gemini API for transcription.

## Features

- Upload audio files via a web interface
- Automatic transcription of audio to text
- Fast, modern React frontend
- Robust Spring Boot backend
- Integration with Gemini API for high-quality transcription

## Tech Stack

- **Frontend:** React, Vite, JavaScript, Axios
- **Backend:** Java, Spring Boot, Maven
- **API:** Gemini (Google Generative Language API)

## Getting Started

### Prerequisites

- Node.js & npm
- Java 17+
- Maven

### Setup

#### 1. Clone the repository

bash
git clone https://github.com/yourusername/Wave2Words.git
cd Wave2Words

2. Backend Setup
Configure your Gemini API key in src/main/resources/application.properties:
gemini.api.key=YOUR_API_KEY
gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent
Build and run the backend:
mvn clean install
mvn spring-boot:run
Backend runs on http://localhost:8080
3. Frontend Setup
Install dependencies and start Vite dev server:
cd audio-transcribe-frontend
npm install
npm run dev
Frontend runs on http://localhost:5173 (default Vite port)
Usage
Open the frontend in your browser.
Upload an audio file.
View the transcribed text.
API Endpoints
POST /api/transcriber
Uploads an audio file and returns the transcription.
CORS
CORS is configured to allow requests from any origin during development.
For production, restrict allowed origins in the backend configuration.


Folder Structure
audio-transcribe-frontend/ — React frontend
src/main/java/com/audio/transciber/ — Spring Boot backend
src/main/resources/ — Backend resources and configuration
Contributing
Pull requests are welcome. For major changes, open an issue first to discuss what you would like to change.


License
This project is licensed under the MIT License.
Acknowledgements
Spring Boot
React
Vite
Gemini API
