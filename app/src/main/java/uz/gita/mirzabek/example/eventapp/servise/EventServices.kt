package uz.gita.mirzabek.example.eventapp.servise

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mirzabek.example.eventapp.MainActivity
import uz.gita.mirzabek.example.eventapp.R
import uz.gita.mirzabek.example.eventapp.broadcast.EventBroadcast

@AndroidEntryPoint
class EventServices :Service(){
    private var eventBroadcast: EventBroadcast? = null

    override fun onBind(p0: Intent?): IBinder? =null

    override fun onCreate() {
        super.onCreate()

        if (eventBroadcast==null){
            eventBroadcast=EventBroadcast()
        }

        createChannel()
        startMyService()

        registerReceiver(eventBroadcast, IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_SHUTDOWN)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_REBOOT)
        })

    }


    private  fun  startMyService(){
        val id = 123
        val notification = NotificationCompat.Builder(this, "Event").apply {
            setSmallIcon(R.drawable.notification_img)
            setContentTitle("Event Manager")
            setCustomContentView(createRemoteView())
            setContentText("This app listen events from System")
            setStyle(NotificationCompat.DecoratedCustomViewStyle())
            setContentIntent(PendingIntent.getActivity(this@EventServices, 0, Intent(this@EventServices, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE))
        }.build()
        startForeground(id, notification)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "Event",
                "Main",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createRemoteView(): RemoteViews {
        val view = RemoteViews(this.packageName, R.layout.remote_view)
        view.setTextViewText(R.id.textMusicName, "This app listen events from System")
        view.setTextViewText(R.id.textArtistName, "The Device Events app is running ")
        view.setOnClickPendingIntent(R.id.buttonCancel, createPendingIntent(ActionEnum.CANCEL))
        return view
    }



    @SuppressLint("UnspecifiedImmutableFlag")
    private fun createPendingIntent(action: ActionEnum): PendingIntent {
        val intent = Intent(this, EventServices::class.java)
        intent.putExtra("COMMAND", action)
        return PendingIntent.getService(this, action.pos, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.let {
            val command=it.getSerializable("COMMAND") as ActionEnum
            if (command == ActionEnum.CANCEL) stopSelf()
        }
        return START_NOT_STICKY
    }
}

enum class ActionEnum(val pos: Int) {
    CANCEL(0)
}