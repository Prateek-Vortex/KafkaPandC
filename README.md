# Kafka as a publisher and consumer

We can publish our message and consume it too with the help of kafka the published messages are also stored in the database.

# Endpoints:

## Post

/kafka/publish/message: This end points takes UserPublishModel and store the data in data base, and also send the data to be published and return the consumed response as UserPublishedModel.

# Model:
## UserPublishedModel:
{
    userName:String,
    userMessage:String
}
