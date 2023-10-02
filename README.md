
# IP Manager API

The IP Manager API is a Java Spring Boot application that allows you to manage IP addresses and perform various operations related to IP address allocation and subnet calculations.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
  - [IP Address Endpoints](#ip-address-endpoints)
  - [Subnet Calculator Endpoint](#subnet-calculator-endpoint)
- [Authentication](#authentication)
- [Contributing](#contributing)

## Features

- Allocate and deallocate IP addresses
- List allocated and available IP addresses
- Perform subnet calculations

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 21 or later installed
- Docker installed (optional, for Docker deployment)
- MySQL database accessible (for database storage)

## Getting Started

### Running the Application

1. Clone the repository:

   ```bash
   git clone https://bitbucket.org/brianbill/ip-management-tool.git
   ```

2. Navigate to the project directory:

   ```bash
   cd ip-manager-tool
   ```

3. Run the Docker Compose file to start the MySQL database:

   ```bash
   docker-compose up -d --build
   ```

The application will be accessible at [http://localhost:8080](http://localhost:8080).

## Endpoints

### IP Address Endpoints

- **GET /ip/allocated**: Get a list of all allocated IP addresses.
```bash
curl --location 'http://localhost:8080/ip/allocated?startIp=192.168.1.10&endIp=192.168.1.20' \
--header 'Authorization: Basic YWRtaW46YWRtaW4='
```
- **GET /ip/available**: Get a list of all available (unallocated) IP addresses.
```bash
curl --location 'http://localhost:8080/ip/available?startIp=192.168.1.10&endIp=192.168.1.20' \
--header 'Authorization: Basic YWRtaW46YWRtaW4='
```
- **POST /ip/allocate**: Allocate a new IP address.
```bash
curl --location 'http://localhost:8080/ip/allocate' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--data-raw '{
    "name": "John Doe",
    "email": "bill@email.com"
}'
```
- **Release IP /ip/release/{ipAddress}**: Deallocate the specified IP address.
```bash
curl --location --request PUT 'http://localhost:8080/ip/release/192.168.1.1' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' 
```

### Subnet Calculator Endpoint

- **GET /subnet/calculate**: Calculate details of an IP subnet.
    - Query Parameters: `ip` (IP address), `subnetMask` (Subnet mask)
```bash
curl --location 'http://localhost:8080/subnet/calculate?ip=192.168.1.10&subnetMask=255.255.255.0' \
--header 'Authorization: Basic YWRtaW46YWRtaW4='
```

## Authentication

The API uses Basic Authentication. Provide your username and password with each request.

## Contributing

To contribute to the project, follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature/your-feature`.
5. Submit a pull request.

## API Documentation

Import the [Postman collection](https://api.postman.com/collections/6268985-cbdd943b-f6ad-4fcd-961f-052aa92f3f99?access_key=PMAT-01HBQY46AWD3K9CPNG2ABAWMF4) to view the API documentation.
