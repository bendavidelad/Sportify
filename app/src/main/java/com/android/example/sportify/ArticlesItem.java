package com.android.example.sportify;

/**
 * Created by eladbendavid on 11/1/18.
 */

public class ArticlesItem {
        private String image;
        private String title;
        private String description;
        private  String url;


        public ArticlesItem() {
            super();
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() { return url; }

        public void setUrl(String url) { this.url = url; }


}


