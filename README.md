# Anki Frequency Tag Adder

Small utility for adding a word frequency tag to your word list that you are learning with Anki. Word frequency is based on number of times a word appeared on the Internet, TV, blogs, social media etc.

# How to use
#####Your deck has to contain English words in the first column
1. Clone this repository
2. [Export your deck in txt format](https://docs.ankiweb.net/#/exporting)
3. Rename your deck to __*deck.txt*__ and put it to **_src\main\resources_** folder of this project 
4. Run App in your IDE
5. Take __*deck_withtags.txt*__ from __*src\main\resources*__
6. In Desktop Anki click Import
7. Choose the newly created deck with tags
8. Select note type and deck that you'll be changing
9. Specify a field separator as **\t**
10. Select 'Update existing notes when first field matches'
11. Map first field to your field containing English words
12. Map second field to tags

#Attention
##All the tags (except marked) you've added to your notes will be deleted