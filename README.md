
# DAS Backend Project

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/your-repo/your-project/actions)
[![Coverage](https://img.shields.io/badge/coverage-85%25-yellowgreen)](https://github.com/your-repo/your-project/actions)
[![Version](https://img.shields.io/badge/version-0.0.1-red)](https://github.com/your-repo/your-project/releases)


## Introduction

This is the backend project for the DAS application, built using Java Spring Boot. This documentation provides details about the endpoints available in the DAS Backend API. Below you will find a list of all the available endpoints, their descriptions, and their current status.

### Quick Links
- [API Documentation](https://das-backend.fly.dev/swagger-ui/index.html)

### Base URL
```
https://das-backend.fly.dev/api
```

### Endpoints

#### Account Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/accounts`                                           | Create a new account. |
| :white_check_mark: | `/api/accounts/phoneregister`                             | Register a new account with a phone number. |
| :white_check_mark: | `/api/accounts/phonelogin`                                | Login with a phone number and password. |
| :white_check_mark: | `/api/accounts/{id}`                                      | Get account details by ID. |
| :white_check_mark: | `/api/accounts`                                           | Get all accounts. |
| :white_check_mark: | `/api/accounts/{id}`                                      | Update account details by ID. |
| :white_check_mark: | `/api/accounts/role/{id}`                                 | Get accounts by role. |
| :white_check_mark: | `/api/accounts/{id}`                                      | Delete account by ID. |
| :white_check_mark: | `/api/accounts/{id}/status/{status}`                      | Change account status by ID. |
| :white_check_mark: | `/api/accounts/{id}/role/{role}`                          | Change account role by ID. |

#### Assessment Booking Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/assessment-bookings`                                | Create a new assessment booking. |
| :white_check_mark: | `/api/assessment-bookings/{id}`                           | Get assessment booking by ID. |
| :white_check_mark: | `/api/assessment-bookings`                                | Get all assessment bookings. |
| :white_check_mark: | `/api/assessment-bookings/proceed/{id}`                   | Proceed with assessment booking by ID. |
| :white_check_mark: | `/api/assessment-bookings/{id}`                           | Update assessment booking by ID. |
| :white_check_mark: | `/api/assessment-bookings/{id}`                           | Delete assessment booking by ID. |
| :white_check_mark: | `/api/assessment-bookings/{id}/status/{status}`           | Change assessment booking status by ID. |
| :white_check_mark: | `/api/assessment-bookings/{id}/feedback`                  | Add feedback to assessment booking by ID. |
| :white_check_mark: | `/api/assessment-bookings/{bookingId}/assign/{staffId}`   | Assign staff to assessment booking by ID. |
| :white_check_mark: | `/api/assessment-bookings/account/{accountId}`            | Get assessment bookings by account ID. |
| :white_check_mark: | `/api/assessment-bookings/ordered`                        | Get all ordered assessment bookings. |

#### Assessment Paper Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/assessment-papers`                                  | Create a new assessment paper. |
| :white_check_mark: | `/api/assessment-papers/{id}`                             | Get assessment paper by ID. |
| :white_check_mark: | `/api/assessment-papers`                                  | Get all assessment papers. |
| :white_check_mark: | `/api/assessment-papers/{id}`                             | Update assessment paper by ID. |
| :white_check_mark: | `/api/assessment-papers/{id}`                             | Delete assessment paper by ID. |

#### Booking Sample Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/booking-samples/sample`                             | Create a new booking sample. |
| :white_check_mark: | `/api/booking-samples/samples`                            | Create multiple booking samples. |
| :white_check_mark: | `/api/booking-samples/{id}`                               | Get booking sample by ID. |
| :white_check_mark: | `/api/booking-samples`                                    | Get all booking samples. |
| :white_check_mark: | `/api/booking-samples/{id}`                               | Update booking sample by ID. |
| :white_check_mark: | `/api/booking-samples/{id}`                               | Delete booking sample by ID. |
| :white_check_mark: | `/api/booking-samples/{id}/status/{status}`               | Change booking sample status by ID. |
| :white_check_mark: | `/api/booking-samples/{sampleId}/assign/{staffId}`        | Assign staff to booking sample by ID. |
| :white_check_mark: | `/api/booking-samples/booking/{bookingId}`                | Get booking samples by booking ID. |
| :white_check_mark: | `/api/booking-samples/assessment-account/{assessmentAccountId}` | Get booking samples by assessment account ID. |
| :white_check_mark: | `/api/booking-samples/count`                              | Count all booking samples by booking ID. |
| :white_check_mark: | `/api/booking-samples/count-by-status`                    | Count booking samples by booking ID with status 1 or 2. |
| :white_check_mark: | `/api/booking-samples/count-sealed`                       | Count booking samples by booking ID with status 4. |

#### Commitment Paper Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/commitment-papers`                                  | Create a new commitment paper. |
| :white_check_mark: | `/api/commitment-papers/{id}`                             | Get commitment paper by ID. |
| :white_check_mark: | `/api/commitment-papers`                                  | Get all commitment papers. |
| :white_check_mark: | `/api/commitment-papers/{id}`                             | Update commitment paper by ID. |
| :white_check_mark: | `/api/commitment-papers/{id}`                             | Delete commitment paper by ID. |
| :white_check_mark: | `/api/commitment-papers/{id}/status`                      | Change commitment paper status by ID. |

#### Seal Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/seals`                                              | Create a new seal. |
| :white_check_mark: | `/api/seals/{id}`                                         | Get seal by ID. |
| :white_check_mark: | `/api/seals`                                              | Get all seals. |
| :white_check_mark: | `/api/seals/{id}`                                         | Update seal by ID. |
| :white_check_mark: | `/api/seals/{id}`                                         | Delete seal by ID. |

#### Service Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/services`                                           | Create a new service. |
| :white_check_mark: | `/api/services/{id}`                                      | Get service by ID. |
| :white_check_mark: | `/api/services`                                           | Get all services. |
| :white_check_mark: | `/api/services/{id}`                                      | Update service by ID. |
| :white_check_mark: | `/api/services/{id}`                                      | Delete service by ID. |
| :white_check_mark: | `/api/services/{serviceId}/update-account`                | Update account ID for service by service ID. |

#### Service Price List Endpoints
| Status             |  Link to API                                              | Description            |
| ------------------ | --------------------------------------------------------- | ---------------------- |
| :white_check_mark: | `/api/service-price-lists`                                | Create a new service price list. |
| :white_check_mark: | `/api/service-price-lists/{id}`                           | Get service price list by ID. |
| :white_check_mark: | `/api/service-price-lists`                                | Get all service price lists. |
| :white_check_mark: | `/api/service-price-lists/{id}`                           | Update service price list by ID. |
| :white_check_mark: | `/api/service-price-lists/{id}`                           | Delete service price list by ID. |
| :white_check_mark: | `/api/service-price-lists/{id}`                           | Update service price list price by ID. |

### Contributing
If you notice issues, have questions or want to suggest enhancements, please raise an issue in the repository.

