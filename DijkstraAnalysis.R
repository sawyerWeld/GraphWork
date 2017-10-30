df <- read.table("DijkstraStats.txt", header = TRUE)
df$Saved <- 100*df$SortTime/df$RunTime
df$Vertices <- as.factor(df$Vertices)

df %>% 
  subset(Vertices == 10) %>%
  group_by(Edges) %>%
  summarise(Avg = mean(RunTime), Avt = mean(SortTime), Saved = Avt/Avg * 100)
  

df %>% 
  subset(Vertices == 20) %>%
  group_by(Edges) %>%
  summarise(Avg = mean(RunTime), Avt = mean(SortTime), Saved = Avt/Avg * 100)


df %>% 
  subset(Vertices == 40) %>%
  group_by(Edges) %>%
  summarise(Avg = mean(RunTime), Avt = mean(SortTime), Saved = Avt/Avg * 100)


a <- df %>% 
  subset(Vertices == 80) %>%
  group_by(Edges) %>%
  summarise(Avg = mean(RunTime), Avt = mean(SortTime), Saved = Avt/Avg * 100)

ggplot(df,aes(RunTime,Saved)) +
  #geom_line(aes(RunTime,Saved),color = Vertices)
  geom_density2d(aes(color = Vertices))

df_cont <- read.table("DijkstraStatsCont.txt", header = TRUE)
df_cont$saved <- 100*df_cont$SortTime/df_cont$RunTime
ggplot(data = df_cont,aes(Vertices,RunTime)) +
  geom_jitter(aes(color=Edges)) +
  ylim(0,200000) +
  scale_colour_gradientn(colours=rainbow(3)) +
  geom_smooth()

ggplot(data = df_cont,aes(RunTime,saved)) +
  geom_point(aes(color=Vertices)) +
  ylim(0,100) +
  #scale_colour_gradientn(colours=rainbow(3)) +
  geom_smooth()



