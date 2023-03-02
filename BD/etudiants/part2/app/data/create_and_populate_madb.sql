CREATE TABLE IF NOT EXISTS user (
                                    login TEXT PRIMARY KEY,
                                    password TEXT NOT NULL,
                                    slug TEXT NOT NULL);

/* password = pass */
INSERT OR REPLACE INTO user (login,password,slug) VALUES ("u1","p1","s1");
INSERT OR REPLACE INTO user (login,password,slug) VALUES ("u2","p2","s2");
INSERT OR REPLACE INTO user (login,password,slug) VALUES ("u3","p3","s3");


